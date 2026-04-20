(defn find_kth
  "	Write a function to find kth element from the given two sorted vectors."
  [arr1 arr2 k]
  (let [a (or arr1 [])
        b (or arr2 [])
        n1 (count a)
        n2 (count b)
        total (+ n1 n2)]
    (when (and (integer? k) (<= 1 k total))
      (loop [i 0
             j 0
             remaining k]
        (cond
          (zero? remaining) nil
          (= i n1) (nth b (+ j (dec remaining)))
          (= j n2) (nth a (+ i (dec remaining)))
          (= remaining 1) (if (<= (nth a i) (nth b j)) (nth a i) (nth b j))
          :else
          (let [step (max 1 (quot remaining 2))
                new-i (min n1 (+ i step))
                new-j (min n2 (+ j step))
                ai (nth a (dec new-i))
                bj (nth b (dec new-j))]
            (if (<= ai bj)
              (recur new-i j (- remaining (- new-i i)))
              (recur i new-j (- remaining (- new-j j))))))))))