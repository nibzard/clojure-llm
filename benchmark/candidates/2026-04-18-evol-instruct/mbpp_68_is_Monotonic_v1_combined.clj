(defn is-monotonic?
  "Return true if the given sequence is monotonic (entirely nondecreasing or nonincreasing).
  Accepts any sequential collection, handles nil as true, and works with empty or single-element collections.

  Examples:
  (is-monotonic? [1 2 2 3]) => true
  (is-monotonic? '(5 4 4 1)) => true
  (is-monotonic? [3 1 2])    => false
  (is-monotonic? nil)        => true"
  [xs]
  (let [xs (seq xs)]
    (or (nil? xs)
        (let [pairs (map vector xs (rest xs))
              nondecreasing? (every? (fn [[a b]] (<= a b)) pairs)
              nonincreasing? (every? (fn [[a b]] (>= a b)) pairs)]
          (or nondecreasing? nonincreasing?)))))

(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= true (is-monotonic? nil)))
  (is (= true (is-monotonic? [])))
  (is (= true (is-monotonic? [1 1 2 3])))
  (is (= true (is-monotonic? '(5 4 4 1))))
  (is (= false (is-monotonic? [1 3 2]))))

(run-test test-variation)
