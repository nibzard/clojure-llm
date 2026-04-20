(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= {"A" "hello", :b "world"}
         (normalize-map {"A" "HELLO" :b "WORLD" nil "SKIP"}))))

(run-test test-variation)
