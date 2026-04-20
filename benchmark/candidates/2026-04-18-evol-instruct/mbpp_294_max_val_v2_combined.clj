(defn max-positive
  "Return the maximum positive number from a collection.

  Ignore nil values and non-numeric items.
  Return nil if there are no positive numbers.

  Examples:
  (max-positive [1 -2 7 nil 4]) ;=> 7
  (max-positive [\"a\" nil -3 0]) ;=> nil
  (max-positive []) ;=> nil"
  [coll]
  (let [nums (filter #(and (number? %) (pos? %)) coll)]
    (when (seq nums)
      (apply max nums))))

(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= 7 (max-positive [1 -2 7 nil 4])))
  (is (= nil (max-positive ["a" nil -3 0])))
  (is (= nil (max-positive []))))

(run-test test-variation)
