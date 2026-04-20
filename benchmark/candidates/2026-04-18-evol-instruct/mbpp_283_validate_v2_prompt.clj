(defn validate
  "Given a collection of integers, return true if each number is valid for its
  own digits: the frequency of every digit in the number must be less than or
  equal to the digit itself.

  Ignore nil values in the collection.

  Examples:
  (validate [22 212 321]) => true
  (validate [11 12 20]) => false
  (validate [nil 22 nil]) => true"
  [nums])