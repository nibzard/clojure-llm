(defn get_Inv_Count
  "Count inversions in a vector. An inversion is a pair (i, j) such that i < j and arr[i] > arr[j]."
  [arr]
  (if (or (nil? arr) (<= (count arr) 1))
    0
    (letfn [(merge-and-count [left right]
              (loop [l left
                     r right
                     merged []
                     inv-count 0]
                (cond
                  (empty? l) [(concat merged r) inv-count]
                  (empty? r) [(concat merged l) inv-count]
                  :else (let [l-first (first l)
                              r-first (first r)]
                          (if (> l-first r-first)
                            (recur (rest l) r (conj merged r-first) (+ inv-count (count l)))
                            (recur l (rest r) (conj merged l-first) inv-count)))))
            )
            (sort-and-count [v]
              (if (<= (count v) 1)
                [v 0]
                (let [mid (quot (count v) 2)
                      [left-left left-right] (sort-and-count (vec (take mid v)))
                      [right-left right-right] (sort-and-count (vec (drop mid v)))
                      [merged inv] (merge-and-count left-left right-left)]
                  [merged (+ left-right right-right inv)])))]
      (second (sort-and-count (vec arr))))))