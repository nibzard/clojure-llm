(defn max_product
  "Write a function to find the maximum product formed by multiplying numbers of an increasing subsequence of that vector."
  [arr]
  (if (empty? arr)
    0
    (let [dp (reduce (fn [acc vi]
                       (let [prevs (filter #(< (:val %) vi) acc)
                             cands (cons vi (mapcat (fn [p] [(* vi (:max p)) (* vi (:min p))]) prevs))
                             max-val (apply max cands)
                             min-val (apply min cands)]
                         (conj acc {:val vi :max max-val :min min-val})))
                     []
                     arr)]
      (apply max (map :max dp)))))