(defn contains-in-order?
  "Return true if all items in `needle` appear in `haystack` in the same order as a subsequence.
  Items do not need to be contiguous.

  Examples:
  (contains-in-order? [1 3] [1 2 3 4]) ;=> true
  (contains-in-order? [:a :c] [:a :b :c]) ;=> true
  (contains-in-order? [2 1] [1 2 3]) ;=> false
  (contains-in-order? [] [1 2 3]) ;=> true"
  [needle haystack]
  (loop [n (seq needle)
         h (seq haystack)]
    (cond
      (nil? n) true
      (nil? h) false
      (= (first n) (first h)) (recur (next n) (next h))
      :else (recur n (next h)))))

(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= true (contains-in-order? [1 3] [1 2 3 4])))
  (is (= true (contains-in-order? [:a :c] [:a :b :c])))
  (is (= false (contains-in-order? [2 1] [1 2 3])))
  (is (= true (contains-in-order? [] [1 2 3]))))

(run-test test-variation)
