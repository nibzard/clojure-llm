(require '[clojure.test :refer [deftest is run-test]])

(def candidate sort_numbers)

(deftest test-humaneval

  (is (= (candidate "") ""))
  (is (= (candidate "three") "three"))
  (is (= (candidate "three five nine") "three five nine"))
  (is (= (candidate "five zero four seven nine eight") "zero four five seven eight nine"))
  (is (= (candidate "six five four three two one zero") "zero one two three four five six"))
)

(run-test test-humaneval)