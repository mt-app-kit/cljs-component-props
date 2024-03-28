
(ns component-props.presets.side-effects
    (:require [common-state.api :as common-state]))

;; ----------------------------------------------------------------------------
;; ----------------------------------------------------------------------------

(defn reg-preset!
  ; @description
  ; Registers a preset map or function.
  ;
  ; @param (keyword) preset-id
  ; @param (function or map) preset
  ;
  ; @usage
  ; (reg-preset! :my-preset {:my-key "My value"})
  ;
  ; @usage
  ; (reg-preset! :my-preset (fn [props] (assoc props :my-key "My value")))
  [preset-id preset]
  (common-state/assoc-state! :component-presets :preset-pool preset-id preset))
