(defn opposite_Signs
  "	Write a cljthon function to check whether the given two integers have opposite sign or not."
  [x y]
  (or (and (pos? x) (neg? y))
      (and (neg? x) (pos? y))))