(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= {"first_name" "Ada" "city" "New_York"}
         (sanitize-keys {:first name "Ada" :last name nil :city "New York"}))))

(run-test test-variation)
