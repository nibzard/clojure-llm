(defn convert
  "Write a cljthon function to convert complex numbers to polar coordinates."
  [numbers]
  (map (fn [[x y]]
         [(Math/sqrt (+ (* x x) (* y y)))
          (Math/atan2 y x)])
       numbers))