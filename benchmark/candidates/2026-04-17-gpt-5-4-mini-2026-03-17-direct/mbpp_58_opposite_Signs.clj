(defn opposite_Signs
  "Write a cljthon function to check whether the given two integers have opposite sign or not."
  [x y]
  (and (number? x)
       (number? y)
       (not (zero? x))
       (not (zero? y))
       (neg? (* x y))))