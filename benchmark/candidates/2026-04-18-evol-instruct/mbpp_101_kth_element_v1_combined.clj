(defn kth-present
  "Return the kth non-nil element from a possibly lazy sequence using 1-based indexing.

  Examples:
  (kth-present [nil 1 nil 2 3] 2) => 2
  (kth-present (range) 5) => 4

  Return nil if k is nil, not positive, or there are fewer than k non-nil elements."
  [xs k]
  (when (and (integer? k) (pos? k))
    (nth (filter some? xs) (dec k) nil)))

(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= 2 (kth-present [nil 1 nil 2 3] 2))))

(run-test test-variation)
