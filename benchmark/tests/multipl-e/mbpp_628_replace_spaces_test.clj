(require '[clojure.test :refer [deftest is run-test]])

(def candidate replace_spaces)

(deftest test-humaneval

  (is (= (candidate "My Name is Dawood") "My%20Name%20is%20Dawood"))
  (is (= (candidate "I am a Programmer") "I%20am%20a%20Programmer"))
  (is (= (candidate "I love Coding") "I%20love%20Coding"))
)

(run-test test-humaneval)