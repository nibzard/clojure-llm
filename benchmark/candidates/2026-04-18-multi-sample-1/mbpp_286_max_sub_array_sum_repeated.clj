(defn max_sub_array_sum_repeated
  "	Write a function to find the largest sum of a contiguous vector in the modified vector which is formed by repeating the given vector k times."
  [a n k]
  (let [v (vec (or a []))
        n' (min (or n 0) (count v))
        xs (subvec v 0 n')
        kadane (fn [coll]
                 (when (seq coll)
                   (reduce (fn [[best curr] x]
                             (let [curr' (max x (+ curr x))
                                   best' (max best curr')]
                               [best' curr']))
                           [(first coll) (first coll)]
                           (rest coll))))
        total (reduce + 0 xs)
        max-prefix (when (seq xs)
                     (reduce (fn [[best s] x]
                               (let [s' (+ s x)]
                                 [(max best s') s']))
                             [Long/MIN_VALUE 0]
                             xs))
        max-suffix (when (seq xs)
                     (reduce (fn [[best s] x]
                               (let [s' (+ s x)]
                                 [(max best s') s']))
                             [Long/MIN_VALUE 0]
                             (rseq xs)))]
    (cond
      (or (zero? n') (<= (or k 0) 0)) 0
      (= k 1) (first (kadane xs))
      :else (let [twice (into xs xs)
                  best-twice (first (kadane twice))
                  pref (first max-prefix)
                  suff (first max-suffix)]
              (if (pos? total)
                (max best-twice (+ suff pref (* (- k 2) total)))
                best-twice)))))