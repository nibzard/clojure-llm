(defn find_kth
  "	Write a function to find kth element from the given two sorted vectors."
  [arr1 arr2 k]
  (let [a (vec (or arr1 []))
        b (vec (or arr2 []))
        n (count a)
        m (count b)
        total (+ n m)]
    (when (and (integer? k) (<= 1 k total))
      (loop [ia 0
             ib 0
             kk k]
        (cond
          (>= ia n) (nth b (+ ib (dec kk)))
          (>= ib m) (nth a (+ ia (dec kk)))
          (= kk 1) (min (nth a ia) (nth b ib))
          :else
          (let [step (quot kk 2)
                new-ia (min n (+ ia step))
                new-ib (min m (+ ib step))
                va (nth a (dec new-ia))
                vb (nth b (dec new-ib))]
            (if (<= va vb)
              (recur new-ia ib (- kk (- new-ia ia)))
              (recur ia new-ib (- kk (- new-ib ib))))))))))