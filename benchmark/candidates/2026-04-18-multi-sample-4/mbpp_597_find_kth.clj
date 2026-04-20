(defn find_kth
  "	Write a function to find kth element from the given two sorted vectors."
  [arr1 arr2 k]
  (let [v1 (or arr1 [])
        v2 (or arr2 [])
        n1 (count v1)
        n2 (count v2)
        total (+ n1 n2)]
    (when (and (integer? k) (<= 1 k total))
      (loop [i 0
             j 0
             remaining k]
        (cond
          (>= i n1) (nth v2 (+ j (dec remaining)))
          (>= j n2) (nth v1 (+ i (dec remaining)))
          (= remaining 1) (min (nth v1 i) (nth v2 j))
          :else (let [step (quot remaining 2)
                      new-i (min n1 (+ i step))
                      new-j (min n2 (+ j step))
                      pivot1 (nth v1 (dec new-i))
                      pivot2 (nth v2 (dec new-j))]
                  (if (<= pivot1 pivot2)
                    (recur new-i j (- remaining (- new-i i)))
                    (recur i new-j (- remaining (- new-j j))))))))))