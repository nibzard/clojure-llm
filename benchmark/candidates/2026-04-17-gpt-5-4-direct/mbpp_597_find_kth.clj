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
          (= i n1) (nth b (+ j (dec remaining)))
          (= j n2) (nth a (+ i (dec remaining)))
          (= remaining 1) (if (<= (nth a i) (nth b j)) (nth a i) (nth b j))
          :else (if (<= (nth a i) (nth b j))
                  (recur (inc i) j (dec remaining))
                  (recur i (inc j) (dec remaining))))))))