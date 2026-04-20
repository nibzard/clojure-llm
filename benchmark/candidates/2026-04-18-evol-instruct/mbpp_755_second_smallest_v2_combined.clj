(defn second-distinct-smallest
  "Return the second distinct smallest number in a collection of numbers.

  If the collection has fewer than two distinct numeric values, return nil.

  Examples:
  (second-distinct-smallest [3 1 2 1 4]) => 2
  (second-distinct-smallest '(5 5 5)) => nil
  (second-distinct-smallest []) => nil
  (second-distinct-smallest nil) => nil"
  [numbers]
  (when-let [sorted (seq (sort (distinct (or numbers []))))]
    (second sorted)))

(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= 2 (second-distinct-smallest [3 1 2 1 4])))
  (is (= nil (second-distinct-smallest '(5 5 5))))
  (is (= nil (second-distinct-smallest [])))
  (is (= nil (second-distinct-smallest nil))))

(run-test test-variation)
