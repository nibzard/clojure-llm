(require '[clojure.test :refer [deftest is run-test]])

(def candidate replace_specialchar)

(deftest test-humaneval

  (is (= (candidate "Python language, Programming language.") "Python:language::Programming:language:"))
  (is (= (candidate "a b c,d e f") "a:b:c:d:e:f"))
  (is (= (candidate "ram reshma,ram rahim") "ram:reshma:ram:rahim"))
)

(run-test test-humaneval)