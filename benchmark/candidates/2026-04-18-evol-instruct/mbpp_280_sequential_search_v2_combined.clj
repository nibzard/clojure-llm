(defn sequential_search
  "Given a collection and a predicate, return a vector of [found? index].
  The index is the first position where the predicate returns true, or -1 if no
  element matches.

  Works on any sequential collection, including lazy sequences.

  Examples:
  (sequential_search [1 3 5 8] even?)
  ;;=> [true 3]

  (sequential_search [1 3 5 7] even?)
  ;;=> [false -1]"
  [coll pred]
  (let [idx (first (keep-indexed (fn [i x] (when (pred x) i)) coll))]
    [(boolean idx) (if (some? idx) idx -1)]))

(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= [true 3] (sequential_search [1 3 5 8] even?)))
  (is (= [false -1] (sequential_search [1 3 5 7] even?)))
  (is (= [true 2] (sequential_search (range) #(= 2 %)))))

(run-test test-variation)
