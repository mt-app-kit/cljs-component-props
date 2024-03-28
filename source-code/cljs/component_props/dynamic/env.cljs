
(ns component-props.dynamic.env
    (:require [common-state.api :as common-state]
              [fruits.map.api   :as map]))

;; ----------------------------------------------------------------------------
;; ----------------------------------------------------------------------------

(defn get-props
  ; @description
  ; Returns the dynamic properties of the component (stored in the common state atom).
  ;
  ; @param (keyword) component-id
  ; @param (map)(opt) default-props
  ;
  ; @usage
  ; (update-props! :my-component assoc :my-prop "My dynamic property")
  ; (get-props     :my-component)
  ; =>
  ; {:my-prop "My dynamic property"}
  ;
  ; @return (map)
  [component-id & [default-props]]
  (if-let [dynamic-props (common-state/get-state :component-props component-id)]
          (-> default-props (map/deep-merge dynamic-props))
          (or default-props {})))

(defn get-prop
  ; @description
  ; Returns a specific dynamic property of the component (stored in the common state atom).
  ;
  ; @param (keyword) component-id
  ; @param (keyword) prop-key
  ; @param (map)(opt) default-props
  ;
  ; @usage
  ; (update-props! :my-component assoc :my-prop "My dynamic property")
  ; (get-prop      :my-component :my-prop)
  ; =>
  ; "My dynamic property"
  ;
  ; @return (*)
  [component-id prop-key & [default-props]]
  (if-let [dynamic-props (common-state/get-state :component-props component-id)]
          (get dynamic-props prop-key)))

;; ----------------------------------------------------------------------------
;; ----------------------------------------------------------------------------

(defn import-props
  ; @description
  ; Merges the dynamic properties of the component (stored in the common state atom) onto the given property map.
  ;
  ; @param (keyword) component-id
  ; @param (map)(opt) component-props
  ; @param (map)(opt) default-props
  ;
  ; @usage
  ; (update-props! :my-component assoc :my-prop "My dynamic property")
  ; (import-props  :my-component {:my-prop "My static property" :another-prop "Another static property"})
  ; =>
  ; {:my-prop      "My dynamic property"
  ;  :another-prop "Another static property"}
  ;
  ; @return (map)
  [component-id & [component-props default-props]]
  (if-let [dynamic-props (common-state/get-state :component-props component-id)]
          (-> component-props (map/deep-merge dynamic-props))
          (or component-props {})))
