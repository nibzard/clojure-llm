(defn find_kth
  "	Write a function to find kth element from the given two sorted vectors."
  [arr1 arr2 k]
  (let [v1 (vec (or arr1 []))
        v2 (vec (or arr2 []))
        n1 (count v1)
        n2 (count v2)
        total (+ n1 n2)]
    (when (and (integer? k) (pos? k) (<= k total))
      (loop [i 0
             j 0
             remaining k]
        (cond
          (= i n1) (nth v2 (+ j (dec remaining)))
          (= j n2) (nth v1 (+ i (dec remaining)))
          (= remaining 1) (if (<= (nth v1 i) (nth v2 j))
                            (nth v1 i)
                            (nth v2 j))
          :else (if (<= (nth v1 i) (nth v2 j))
                  (recur (inc i) j (dec remaining))
                  (recur i (inc j) (dec remaining)))))))