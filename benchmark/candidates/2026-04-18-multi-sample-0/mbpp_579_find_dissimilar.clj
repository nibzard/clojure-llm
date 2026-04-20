(defn find_dissimilar
  "	Write a function to find the dissimilar elements in the given two vectors."
  [test_tup1 test_tup2]
  (let [freq1 (frequencies (or test_tup1 []))
        freq2 (frequencies (or test_tup2 []))
        keys-all (clojure.set/union (set (keys freq1)) (set (keys freq2)))]
    (vec
     (mapcat
      (fn [k]
        (repeat (Math/abs ^long (- (get freq1 k 0) (get freq2 k 0))) k))
      keys-all))))