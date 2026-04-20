(defn move_one_ball
  "We have a vector 'arr' of N integers arr[1], arr[2], ..., arr[N].The
  numbers in the vector will be randomly ordered. Your task is to determine if
  it is possible to get a vector sorted in non-decreasing order by performing 
  the following operation on the given vector:
      You are allowed to perform right shift operation any number of times.
  One right shift operation means shifting all elements of the vector by one
  position in the right direction. The last element of the vector will be moved to
  the starting position in the vector i.e. 0th index. 
  If it is possible to obtain the sorted vector by performing the above operation
  then return true else return false.
  If the given vector is empty then return true.
  Note: The given list is guaranteed to have unique elements.
  For Example:
  >>> (move_one_ball [3 4 5 1 2])
  true
  Explanation: By performin 2 right shift operations, non-decreasing order can
               be achieved for the given vector.
  >>> (move_one_ball [3 5 4 1 2])
  false
  Explanation:It is not possible to get non-decreasing order for the given
              vector by performing any number of right shift operations."
  [arr]
  (let [v (vec arr)
        n (count v)]
    (or (<= n 1)
        (<= (count (filter true?
                           (map < v (rest v))))
            (dec n))
        (= 1
           (count (filter true?
                          (map > v (rest v))))))))