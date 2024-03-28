
(ns component-props.dynamic.side-effects
    (:require [common-state.api :as common-state]
              [fruits.map.api   :as map]))

;; ----------------------------------------------------------------------------
;; ----------------------------------------------------------------------------
 
(defn update-props!
  ; @description
  ; Updates the dynamic properties of the component (stored in the common state atom).
  ;
  ; @param (keyword) component-id
  ; @param (function) f
  ; @param (list of *)(opt) params
  ;
  ; @usage
  ; (update-props! :my-component assoc :my-prop "My dynamic property")
  [component-id f & params]
  (letfn [(f0 [%] (apply f % params))]
         (common-state/update-state! :component-props component-id f0)))

(defn merge-props!
  ; @description
  ; Merges the given map onto the dynamic properties of the component (stored in the common state atom).
  ;
  ; @param (keyword) component-id
  ; @param (map) updated-props
  ;
  ; @usage
  ; (merge-props! :my-component {:my-prop "My dynamic property"})
  [component-id updated-props]
  (update-props! component-id map/deep-merge updated-props))

(defn clear-props!
  ; @description
  ; Clears the dynamic properties of the component (stored in the common state atom).
  ;
  ; @param (keyword) component-id
  ;
  ; @usage
  ; (clear-props! :my-component)
  [component-id]
  (common-state/dissoc-state! :component-props component-id))
