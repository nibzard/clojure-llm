(defn convert
  "	Write a cljthon function to convert complex numbers to polar coordinates."
  [numbers]
  (mapv (fn [[x y]]
          (let [r (Math/sqrt (+ (* x x) (* y y)))
                theta (Math/atan2 y x)]
            [r theta]))
        numbers))