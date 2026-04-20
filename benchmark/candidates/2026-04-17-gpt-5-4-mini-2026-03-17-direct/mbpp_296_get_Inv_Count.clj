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
                (recur l (rest r) (conj merged (first r)) (+ inv (count l))))))

          (sort-count [xs]
            (if (<= (count xs) 1)
              [xs 0]
              (let [mid (quot (count xs) 2)
                    [left right] (split-at mid xs)
                    [sl il] (sort-count left)
                    [sr ir] (sort-count right)
                    [merged im] (merge-count sl sr)]
                [merged (+ il ir im)])))]
    (second (sort-count (vec (or arr []))))))