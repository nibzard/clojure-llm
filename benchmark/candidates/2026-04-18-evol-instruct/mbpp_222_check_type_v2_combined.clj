(defn common-type?
  "Return true if every non-nil element in the collection has the same type.
Examples:
  (common-type? [1 2 3])        ;;=> true
  (common-type? [1 nil 2])      ;;=> true
  (common-type? [1 \"a\" 3])     ;;=> false
  (common-type? nil)            ;;=> true"
  [coll]
  (let [types (->> coll
                   (remove nil?)
                   (map type)
                   distinct)]
    (<= (count types) 1)))

(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= true (common-type? [1 2 3])))
  (is (= true (common-type? [1 nil 2])))
  (is (= false (common-type? [1 "a" 3]))))

(run-test test-variation)
