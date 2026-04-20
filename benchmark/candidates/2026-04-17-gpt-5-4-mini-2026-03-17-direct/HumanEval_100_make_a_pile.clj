(defn make_a_pile
  [n]
  (let [step (if (odd? n) 2 2)]
    (take n (iterate #(+ % step) n))))