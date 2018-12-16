(set-env!
  :source-paths #{"src"}
  :resource-paths #{"resources"}
  :dependencies '[[org.clojure/clojure "1.9.0" :scope "provided"]
                  [adzerk/boot-cljs "2.1.4" :scope "test"]
                  [adzerk/boot-reload "0.5.2" :scope "test"]
                  [pandeiro/boot-http "0.8.3" :scope "test"
                   :exclusions [org.clojure/clojure]]
                  [javax.xml.bind/jaxb-api "2.3.0" :scope "test"] ; necessary for Java 9 compatibility
                  [org.clojure/clojurescript "1.10.312" :scope "test"]
                  [nightlight "RELEASE"]
                  [edna "1.6.0"]])

(require
  '[edna.core]
  '[edna-giant-steps.core]
  '[nightlight.boot :refer [nightlight]]
  '[adzerk.boot-cljs :refer [cljs]]
  '[adzerk.boot-reload :refer [reload]]
  '[pandeiro.boot-http :refer [serve]]
  '[clojure.java.io :as io])

(deftask run []
  (comp
    (watch)
    (with-pass-thru _
      (edna-giant-steps.core/-main))
    (nightlight :port 4000)))

(deftask build []
  (let [output (io/file "target" "edna-giant-steps.mp3")]
    (.mkdir (.getParentFile output))
    (with-pass-thru _
      (edna.core/export!
        (edna-giant-steps.core/read-music)
        {:type :mp3
         :out output})
      (println "Built" (.getCanonicalPath output)))))

(deftask run-cljs []
  (comp
    (serve :dir "target/public" :port 3000)
    (watch)
    (reload)
    (cljs
      :optimizations :none
      :compiler-options {:asset-path "main.out"})
    (target)
    (nightlight :port 4000 :url "http://localhost:3000")))

(deftask build-cljs []
  (comp
    (cljs :optimizations :advanced)
    (target)))

