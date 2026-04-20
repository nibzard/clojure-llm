(defn encode
  "Write a function that takes a collection of integers and returns a new
  collection where:
  - every even number is doubled
  - every odd number is replaced with the sum of its digits
  - nil values are ignored

  The function must work for both vectors and lists, and preserve the input
  collection type.

  Examples:
  >>> (encode [1 2 3 4 nil 11])
  [1 4 3 8 2]
  >>> (encode '(5 6 nil 7))
  (5 12 7)"
  [xs]
  (letfn [(digit-sum [n]
            (reduce + (map #(Character/digit % 10) (str (Math/abs (long n))))))
          (f [x]
            (cond
              (nil? x) nil
              (even? x) (* 2 x)
              :else (digit-sum x)))]
    (into (empty xs)
          (comp (filter some?) (map f))
          xs)))

(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= [1 4 3 8 2] (encode [1 2 3 4 nil 11])))
  (is (= '(5 12 7) (encode '(5 6 nil 7)))))

(run-test test-variation)
