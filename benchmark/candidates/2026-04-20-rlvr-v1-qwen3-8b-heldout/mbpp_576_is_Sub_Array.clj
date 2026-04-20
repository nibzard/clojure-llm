(defn is_Sub_Array
  "	Write a cljthon function to check whether a list is sublist of another or not."
  [A B]
  (let [a (or A [])
        b (or B [])]
    (loop [as a
           bs b
           seen (count bs)]
      (cond
        (zero? seen) true
        (empty? as) false
        (not= (first as) (first bs)) (recur (rest as) b (dec seen))
        :else (recur (rest as) (rest bs) (dec seen))))))