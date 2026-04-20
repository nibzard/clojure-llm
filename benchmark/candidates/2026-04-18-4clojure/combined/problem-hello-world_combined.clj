(defn problem-hello-world [a]
  (str "Hello, " a "!"))

(require '[clojure.test :refer [deftest is run-tests]])

(deftest test-problem-hello-world
  (is (= (= (problem-hello-world "Dave") "Hello, Dave!")))
  (is (= (= (problem-hello-world "Jenn") "Hello, Jenn!")))
  (is (= (= (problem-hello-world "Rhea") "Hello, Rhea!"))))

(run-tests)
