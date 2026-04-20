(defn cube-strings
  "Return a lazy sequence of strings, one for each number in nums, with each element formatted as \"n^3=куб\" where n is the original number and куб is its cube.
  
  Examples:
  (cube-strings [2 3])
  ;; => (\"2^3=8\" \"3^3=27\")
  
  Works with any sequence, including infinite ones."
  [nums]
  (map (fn [n] (str n "^3=" (* n n n))) nums))

(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= ["2^3=8" "3^3=27"] (doall (cube-strings [2 3])))))

(run-test test-variation)
