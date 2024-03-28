
(ns component-props.presets.env
    (:require [common-state.api  :as common-state]
              [fruits.map.api    :as map]
              [fruits.vector.api :as vector]))

;; ----------------------------------------------------------------------------
;; ----------------------------------------------------------------------------

(defn apply-preset
  ; @ignore
  ;
  ; @description
  ; Applies the given preset on the given component property map.
  ;
  ; @param (map) component-props
  ; @param (keyword) preset
  ;
  ; @usage
  ; (apply-preset {...} :my-preset)
  ;
  ; @return (map)
  [component-props preset]
  (if-let [preset (common-state/get-state :preset-props :preset-pool preset)]
          (letfn [(f0 [%] (preset %))
                  (f1 [%] (map/reversed-deep-merge % preset))]
                 (cond-> component-props (-> preset fn?)  (f0)
                                         (-> preset map?) (f1)))
          (-> component-props)))

(defn apply-presets
  ; @description
  ; Applies its presets (if any) on the given component property map.
  ;
  ; @param (map) component-props
  ; {:presets (keywords in vector)(opt)
  ;  ...}
  ;
  ; @usage
  ; (apply-presets {:presets [:my-preset]})
  ;
  ; @usage
  ; (reg-preset! :my-preset {:my-key "My value"})
  ; (apply-presets {:presets [:my-preset] ...})
  ; =>
  ; {:my-key "My value"
  ;  ...}
  ;
  ; @return (map)
  [{:keys [presets] :as component-props}]
  (letfn [(f0 [component-props preset] (-> component-props (f1 preset) (f2 preset)))
          (f1 [component-props preset] (apply-preset  component-props preset))
          (f2 [component-props preset] (apply-presets component-props))]
         (if (-> presets vector? not)
             (-> component-props)
             (reduce f0 (dissoc component-props :presets) presets))))
