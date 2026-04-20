(defn find_dissimilar
  "Write a function to find the dissimilar elements in the given two vectors."
  [test_tup1 test_tup2]
  (let [s1 (set test_tup1)
        s2 (set test_tup2)]
    (clojure.set/union (clojure.set/difference s1 s2)
                       (clojure.set/difference s2 s1))))