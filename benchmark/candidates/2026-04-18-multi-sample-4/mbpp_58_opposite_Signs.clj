(defn opposite_Signs
  "	Write a cljthon function to check whether the given two integers have opposite sign or not."
  [x y]
  (cond
    (or (nil? x) (nil? y)) false
    (zero? x) false
    (zero? y) false
    :else (not= (neg? x) (neg? y))))