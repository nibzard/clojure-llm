(defn find_Rotations
  "Write a cljthon function to find the minimum number of rotations (greater than 0) required to get the same string."
  [str]
  (let [s (or str "")
        n (count s)]
    (cond
      (zero? n) 0
      :else
      (or
       (first
        (for [k (range 1 (inc n))
              :when (= s (str (subs s k) (subs s 0 k)))]
          k))
       n))))