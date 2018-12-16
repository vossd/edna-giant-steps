(ns edna-giant-steps.core
  (:require-macros [edna-giant-steps.music]
                   [edna-giant-steps.core :refer [build-for-cljs]]))

(defonce audio (js/document.createElement "audio"))
(set! (.-src audio) (build-for-cljs))
(set! (.-controls audio) true)
(js/document.body.appendChild audio)
(.play audio)

