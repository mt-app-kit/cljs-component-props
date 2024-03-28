
(ns component-props.api
    (:require [component-props.dynamic.env          :as dynamic.env]
              [component-props.dynamic.side-effects :as dynamic.side-effects]
              [component-props.presets.env          :as presets.env]
              [component-props.presets.side-effects :as presets.side-effects]))

;; ----------------------------------------------------------------------------
;; ----------------------------------------------------------------------------

; @tutorial Dynamic properties
;
; Dynamic properties of components are stored in a state atom, updated by functions
; and should be merged onto the static properties of the component.

;; ----------------------------------------------------------------------------
;; ----------------------------------------------------------------------------

; @tutorial Property presets
;
; Property presets of components are registered reusable preset maps or functions
; applied on the property map of the component.

;; ----------------------------------------------------------------------------
;; ----------------------------------------------------------------------------

; @tutorial Demo
;
; @usage
; (reg-preset! :my-preset {:target :_blank}
;
; (defn my-anchor
;   [attributes]
;   (let [attributes (apply-presets attributes)]
;        [:a attributes "My anchor"]))
;
; (my-anchor {:href "/my-uri" :presets [:my-preset]})
; =>
; [:a {:href "/my-uri" :target :_blank}]
;
; @usage
; (defn my-button
;   []
;   [:button {:on-click #(merge-props! :my-button {:background-color :red})
;             :style     (import-props :my-button {})}
;            "My button"])

;; ----------------------------------------------------------------------------
;; ----------------------------------------------------------------------------

; @redirect (component-props.dynamic.env/*)
(def get-props    dynamic.env/get-props)
(def get-prop     dynamic.env/get-prop)
(def import-props dynamic.env/import-props)

; @redirect (component-props.dynamic.side-effects/*)
(def update-props! dynamic.side-effects/update-props!)
(def merge-props!  dynamic.side-effects/merge-props!)
(def clear-props!  dynamic.side-effects/clear-props!)

; @redirect (component-props.presets.env/*)
(def apply-presets presets.env/apply-presets)

; @redirect (component-props.presets.side-effects/*)
(def reg-preset! presets.side-effects/reg-preset!)
