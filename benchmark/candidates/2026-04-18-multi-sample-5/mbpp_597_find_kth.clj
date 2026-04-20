(defn find_kth
  "	Write a function to find kth element from the given two sorted vectors."
  [arr1 arr2 k]
  (let [a (or arr1 [])
        b (or arr2 [])
        n (count a)
        m (count b)
        k (dec k)]
    (when (and (<= 0 k) (< k (+ n m)))
      (loop [a-start 0
             a-len n
             b-start 0
             b-len m
             k k]
        (cond
          (zero? a-len) (nth b (+ b-start k))
          (zero? b-len) (nth a (+ a-start k))
          (zero? k) (let [av (nth a a-start)
                          bv (nth b b-start)]
                      (if (<= av bv) av bv))
          :else
          (let [i (min a-len (max 1 (quot (inc k) 2)))
                j (min b-len (max 1 (quot (inc k) 2)))
                ai (nth a (+ a-start i -1))
                bj (nth b (+ b-start j -1))]
            (if (<= ai bj)
              (recur (+ a-start i) (- a-len i) b-start b-len (- k i))
              (recur a-start a-len (+ b-start j) (- b-len j) (- k j)))))))))