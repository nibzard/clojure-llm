(defn is_Monotonic
  "	Write a cljthon function to check whether the given vector is monotonic or not."
  [A]
  (let [v (vec A)]
    (or (<= (count v) 1)
        (apply <= v)
        (apply >= v))))