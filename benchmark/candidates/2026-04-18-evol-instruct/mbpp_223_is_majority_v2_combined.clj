(defn majority-by
  "Return the first item whose frequency is greater than half the collection size.
  Accepts any sequential collection, including nil and empty collections.
  
  Examples:
  (majority-by [1 2 2 2 3])   ;=> 2
  (majority-by [\"a\" \"b\" \"a\" \"a\"]) ;=> \"a\"
  (majority-by nil)          ;=> nil"
  [coll]
  (when-let [s (seq coll)]
    (let [threshold (/ (count s) 2.0)]
      (->> s
           frequencies
           (some (fn [[k v]]
                   (when (> v threshold) k)))))))

(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= 2 (majority-by [1 2 2 2 3])))
  (is (= "a" (majority-by ["a" "b" "a" "a"])))
  (is (= nil (majority-by nil))))

(run-test test-variation)
