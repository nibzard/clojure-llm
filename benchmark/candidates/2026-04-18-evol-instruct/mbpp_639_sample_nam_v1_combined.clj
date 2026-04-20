(defn sum-upper-name-lengths
  "Return the total length of all names that start with an uppercase letter.

  Examples:
  (sum-upper-name-lengths [\"Alice\" \"bob\" \"Carol\"]) ;=> 10
  (sum-upper-name-lengths nil) ;=> 0
  (sum-upper-name-lengths [\"\" \"Zed\" \"xavier\"]) ;=> 3"
  [names]
  (transduce
    (filter #(and (string? %) (not (empty? %)) (Character/isUpperCase ^char (first %))))
    (completing (fn [acc s] (+ acc (count s))))
    0
    (or names [])))

(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= 10 (sum-upper-name-lengths ["Alice" "bob" "Carol"]))))

(run-test test-variation)
