;; A simple "improvisation" on John Coltrane's "Giant Steps"

(ns edna-giant-steps.music)

(def bpm 250) 
(def choruses 3) 

(def b-maj       [:b :c# :d# :f# :r]) 
(def g-maj       [:g :a :b :d :r]) 
(def e-flat-maj  [:e= :f :g :b= :r]) 
(def d-dom       [:c :d :e :f# :a :r]) 
(def b-flat-dom  [:b= :c :d :f :a= :r]) 
(def f-sharp-dom [:f# :g# :a# :c# :e :r]) 
(def a-min       [:a :c :e :g]) 
(def f-min       [:f :a= :c :e=]) 
(def c-sharp-min [:c# :e :g# :b]) 

(def bass   
  [:acoustic-bass {:octave 2, :tempo bpm} 1/2     
   (repeat choruses       
     [:b :d :g :b= :e= :+e= :-a :d               
      :g :b= :e= :f# :-b :b :f :b=              
      :e= :+e= :a :d :g :-g :c# :f#              
      :-b :b :f :b= :e= :+e= :c# :f#])    
   1/2 :b])

(defn improvise []  
  (->> (map shuffle 
         [b-maj d-dom g-maj b-flat-dom e-flat-maj e-flat-maj a-min d-dom                                                           
          g-maj b-flat-dom e-flat-maj f-sharp-dom b-maj b-maj f-min b-flat-dom                                                           
          e-flat-maj e-flat-maj a-min d-dom g-maj g-maj c-sharp-min f-sharp-dom                                                           
          b-maj b-maj f-min b-flat-dom e-flat-maj e-flat-maj c-sharp-min f-sharp-dom])       
       (map #(take 4 %)))) 
 
(def solo   
  [:midi-electric-piano-1 {:tempo bpm} 1/8    
   [(repeatedly choruses improvise)     
    1/2 :d#]]) 

;;#{bass solo} 

