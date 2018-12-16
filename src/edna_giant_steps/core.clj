(ns edna-giant-steps.core
  (:require [edna.core :as edna]))

(defn read-music []
  (load-file "src/edna_giant_steps/music.clj"))

(defonce state (atom nil))

(defn -main []
  (swap! state edna/stop!)
  (reset! state (edna/play! (read-music))))

(defmacro build-for-cljs []
  (edna/edna->data-uri (read-music)))

