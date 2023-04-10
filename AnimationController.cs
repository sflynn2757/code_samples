using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class AnimationController : MonoBehaviour
{
    Animator animator;
    public Interactable interactScript;

    // Start is called before the first frame update
    void Start()
    {
        //get animator component of the player model
        animator = GetComponent<Animator>();
    }

    // Update is called once per frame
    void Update()
    {
        //get the bools from the animator
        bool isWalking = animator.GetBool("isWalking");
        bool isWalkingBackwards = animator.GetBool("isWalkingBackwards");
        bool isWalkingLeft = animator.GetBool("isWalkingLeft");
        bool isWalkingRight = animator.GetBool("isWalkingRight");
        bool isInteracting = animator.GetBool("isInteracting");
        //check input data
        bool forwardPressed = Input.GetKey("w");
        bool backwardsPressed = Input.GetKey("s");
        bool leftPressed = Input.GetKey("a");
        bool rightPressed = Input.GetKey("d");
        bool interactPressed = Input.GetKey("e");

        //if w is pressed
        if (!isWalking && forwardPressed) {
            animator.SetBool("isWalking", true);

        }
        //if a is pressed
        if (!isWalkingLeft && leftPressed) {
            animator.SetBool("isWalkingLeft", true);
        }
        //if s is pressed
        if (!isWalkingBackwards && backwardsPressed)
        {
            animator.SetBool("isWalkingBackwards", true);
        }
        //if d is pressed
        if (!isWalkingRight && rightPressed)
        {
            animator.SetBool("isWalkingRight", true);
        }
        //if w is let go
        if (isWalking && !forwardPressed)
        {
            animator.SetBool("isWalking", false);
        }
        //if a is let go
        if (isWalkingLeft && !leftPressed)
        {
            animator.SetBool("isWalkingLeft", false);
        }
        //if s is let go
        if (isWalkingBackwards && !backwardsPressed)
        {
            animator.SetBool("isWalkingBackwards", false);
        }
        //if d is let go
        if (isWalkingRight && !rightPressed)
        {
            animator.SetBool("isWalkingRight", false);
        }

        //interact functionality
        //checks interact script for collider trigger boolean
        if (interactScript.isInteractable) {
            //checks animation states and input
            if (!isInteracting && interactPressed)
            {
                animator.SetBool("isInteracting", true);
            }
        }
        //regardless if youre connected to a trigger animation state changes
        if (isInteracting && !interactPressed)
        {
            animator.SetBool("isInteracting", false);
        }
    }
}
