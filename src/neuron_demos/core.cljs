(ns neuron-demos.core
  (:require [quil.core :as q :include-macros true]
            [quil.middleware :as m]))

(defn setup []
  (q/frame-rate 120)
  (q/color-mode :hsb)
  {:color 0
   :angle 0})

(defn update [state]
  (let [{:keys [color angle]} state]
    {:color (mod (+ color 0.7) 255)
     :angle (mod (+ angle 0.1) q/TWO-PI)}))

(defn draw [state]
  (q/background 240)
  (q/fill (:color state) 255 255)
  (let [angle (:angle state)
        x (* 150 (q/cos angle))
        y (* 150 (q/sin angle))
        w 100 h 100
        x2 (+ x 100)
        y2 (+ y 100)
        ]
    (q/with-translation [(/ (q/width) 2)
                         (/ (q/height) 2)]
      ;(q/ellipse x y 100 100)
      (q/rect x y w h)
      )))

(q/defsketch neuron-demos
  :host "neuron-demos"
  :size [500 500]
  :setup setup
  :update update
  :draw draw
  :middleware [m/fun-mode])
