(defn find_Rotations
  "	Write a cljthon function to find the minimum number of rotations (greater than 0) required to get the same string."
  [str]
  (let [s (or str "")
        n (count s)]
    (cond
      (zero? n) 0
      :else
      (or
       (some (fn [k]
               (when (= s (str (subs s k) (subs s 0 k)))
                 k))
             (range 1 (inc n)))
       n))))