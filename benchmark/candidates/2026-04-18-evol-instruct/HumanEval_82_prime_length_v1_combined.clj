(defn prime-count
  "Write a function that takes a collection and returns true if the number of
  distinct elements in the collection is a prime number, or false otherwise.

  The function should work with any sequential collection, including lazy
  sequences.

  Examples
  >>> (prime-count [1 2 2 3 3 3])
  true
  >>> (prime-count '(\"a\" \"b\" \"c\" \"c\"))
  true
  >>> (prime-count [])
  false
  >>> (prime-count (range 10))
  false"
  [coll]
  (let [n (count (distinct coll))]
    (and (> n 1)
         (not-any? #(zero? (mod n %))
                   (range 2 (inc (Math/sqrt n)))))))

(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= true (prime-count [1 2 2 3 3 3])))
  (is (= true (prime-count '("a" "b" "c" "c"))))
  (is (= false (prime-count [])))
  (is (= false (prime-count (range 10)))))

(run-test test-variation)
