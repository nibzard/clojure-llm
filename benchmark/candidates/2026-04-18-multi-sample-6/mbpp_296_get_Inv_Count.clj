(defn get_Inv_Count
  "	Write a cljthon function to count inversions in a vector."
  [arr]
  (letfn [(merge-count [left right]
            (loop [l left
                   r right
                   merged []
                   inv 0]
              (cond
                (empty? l) [(into merged r) inv]
                (empty? r) [(into merged l) inv]
                (<= (first l) (first r))
                (recur (rest l) r (conj merged (first l)) inv)
                :else
                (recur l
                       (rest r)
                       (conj merged (first r))
                       (+ inv (count l))))))
          (sort-count [xs]
            (let [n (count xs)]
              (if (<= n 1)
                [xs 0]
                (let [mid (quot n 2)
                      [left inv-left] (sort-count (subvec xs 0 mid))
                      [right inv-right] (sort-count (subvec xs mid n))
                      [merged inv-merge] (merge-count left right)]
                  [merged (+ inv-left inv-right inv-merge)]))))]
    (if (or (nil? arr) (empty? arr))
      0
      (second (sort-count (vec arr))))))