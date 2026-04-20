(defn sum-odd-index-squares
  "You are given a collection of numbers.
  Return the sum of the squares of the values at odd indexes only (0-based indexing).
  Treat nil as an empty collection.
  Works with any sequential collection.

  Examples:
  >>> (sum-odd-index-squares [1 2 3 4 5]) ; 2^2 + 4^2
  20
  >>> (sum-odd-index-squares '(10 20 30 40)) ; 20^2 + 40^2
  2000
  >>> (sum-odd-index-squares nil)
  0"
  [xs]
  (->> (or xs [])
       (map-indexed vector)
       (filter #(odd? (first %)))
       (map #(let [x (second %)] (* x x)))
       (reduce + 0)))

(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= 20 (sum-odd-index-squares [1 2 3 4 5]))))

(run-test test-variation)
