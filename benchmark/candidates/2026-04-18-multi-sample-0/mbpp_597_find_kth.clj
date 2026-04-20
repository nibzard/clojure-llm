(defn find_kth
  "	Write a function to find kth element from the given two sorted vectors."
  [arr1 arr2 k]
  (let [a (vec (or arr1 []))
        b (vec (or arr2 []))
        n (count a)
        m (count b)]
    (when (and (integer? k) (pos? k) (<= k (+ n m)))
      (loop [i 0
             j 0
             remaining k]
        (cond
          (>= i n) (nth b (+ j (dec remaining)))
          (>= j m) (nth a (+ i (dec remaining)))
          (= remaining 1) (if (<= (nth a i) (nth b j)) (nth a i) (nth b j))
          :else (let [step (quot remaining 2)
                      new-i (min n (+ i step))
                      new-j (min m (+ j step))
                      ai (nth a (dec new-i))
                      bj (nth b (dec new-j))]
                  (if (<= ai bj)
                    (recur new-i j (- remaining (- new-i i)))
                    (recur i new-j (- remaining (- new-j j)))))))))