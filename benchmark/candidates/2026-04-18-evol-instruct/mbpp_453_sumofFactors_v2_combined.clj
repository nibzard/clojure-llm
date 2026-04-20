(defn sum-of-even-factors
  "Return the sum of the even factors of n, or nil when n is nil.

   Examples:
   (sum-of-even-factors 12) => 18
   (sum-of-even-factors 7)  => 0
   (sum-of-even-factors nil) => nil"
  [n]
  (when (some? n)
    (->> (range 1 (inc (Math/abs (long n))))
         (filter #(and (zero? (mod n %))
                     (even? %)))
         (reduce + 0))))

(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= 18 (sum-of-even-factors 12)))
  (is (= 0 (sum-of-even-factors 7)))
  (is (= nil (sum-of-even-factors nil))))

(run-test test-variation)
