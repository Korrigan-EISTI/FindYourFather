package com.dreamteam.findyourfather.web;

import org.springframework.web.bind.annotation.*;
import com.dreamteam.findyourfather.dao.InvitationRepository;
import com.dreamteam.findyourfather.entities.Invitation;

import java.util.List;

@RestController
@RequestMapping("/invitations")
public class InvitationController {

    private final InvitationRepository invitationRepository;

    public InvitationController(InvitationRepository invitationRepository) {
        this.invitationRepository = invitationRepository;
    }

    @GetMapping("/")
    public List<Invitation> getAllInvitations() {
        return (List<Invitation>) invitationRepository.findAll();
    }

    @GetMapping("/{id}")
    public Invitation getInvitationById(@PathVariable Long id) {
        return invitationRepository.findById(id).orElse(null);
    }

    @PostMapping("/")
    public void addInvitation(@RequestBody Invitation invitation) {
        invitationRepository.save(invitation);
    }

    @PutMapping("/{id}")
    public void updateInvitation(@PathVariable Long id, @RequestBody Invitation updatedInvitation) {
        invitationRepository.findById(id).ifPresent(invitation -> {
            invitation.setIdUser1(updatedInvitation.getIdUser1());
            invitation.setIdUser2(updatedInvitation.getIdUser2());
            invitation.setStatus(updatedInvitation.getStatus());
            invitationRepository.save(invitation);
        });
    }

    @DeleteMapping("/{id}")
    public void deleteInvitation(@PathVariable Long id) {
        invitationRepository.deleteById(id);
    }
}
